package com.basaki;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.maven.model.building.DefaultModelBuilder;
import org.apache.maven.model.building.DefaultModelBuilderFactory;
import org.apache.maven.model.building.DefaultModelBuildingRequest;
import org.apache.maven.model.resolution.ModelResolver;
import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.RequestTrace;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.impl.ArtifactResolver;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.impl.RemoteRepositoryManager;
import org.eclipse.aether.impl.VersionRangeResolver;
import org.eclipse.aether.internal.impl.DefaultRepositorySystem;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.file.FileTransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;
import org.eclipse.aether.transport.wagon.WagonTransporterFactory;

public class EffectivePomBuilderOld {

    private static final String WORKING_DIR =
            "/Users/indra.basak/Development/examples/stackoverflow-indra/effective-pom/target";

    public static void main(String[] args) throws Exception {
        DefaultServiceLocator locator = serviceLocator();
        RepositorySystem system = locator.getService(RepositorySystem.class);
        DefaultRepositorySystemSession
                session = MavenRepositorySystemUtils.newSession();
        LocalRepository localRepo = new LocalRepository(WORKING_DIR + "/m2");
        session.setLocalRepositoryManager(
                system.newLocalRepositoryManager(session, localRepo));

        Constructor<ModelResolver> c = modelResolverCtor();
        RequestTrace requestTrace = new RequestTrace(null);
        ArtifactResolver
                artifactResolver = locator.getService(ArtifactResolver.class);
        VersionRangeResolver
                versionRangeResolver =
                locator.getService(VersionRangeResolver.class);
        RemoteRepositoryManager
                remoteRepositoryManager =
                locator.getService(RemoteRepositoryManager.class);
        List<RemoteRepository> repos =
                //                List.of(new RemoteRepository.Builder("central", "default",
                //                        "https://repo.maven.apache.org/maven2/").build());
                Arrays.asList(new RemoteRepository.Builder("central", "default",
                        "https://repo.maven.apache.org/maven2/").build());
        ModelResolver modelResolver =
                c.newInstance(session, requestTrace, "context",
                        artifactResolver, versionRangeResolver,
                        remoteRepositoryManager, repos);

        DefaultRepositorySystem repositorySystem =
                new DefaultRepositorySystem();
        repositorySystem.initService(locator);


        DefaultModelBuildingRequest modelBuildingRequest =
                new DefaultModelBuildingRequest();
        String springBootPOMPath =
                "https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot/2.1.4.RELEASE/spring-boot-2.1.4.RELEASE.pom";
        //https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot/2.1.4.RELEASE/spring-boot-2.1.4.RELEASE.pom
        File springBootPOM = downloadPOM(springBootPOMPath,
                HttpClientBuilder.create().build());
        modelBuildingRequest.setPomFile(springBootPOM);
        modelBuildingRequest.setModelResolver(modelResolver);
        DefaultModelBuilder
                modelBuilder = new DefaultModelBuilderFactory().newInstance();
        System.out.println(modelBuilder.build(
                modelBuildingRequest).getEffectiveModel().getDependencies());
    }

    private static DefaultServiceLocator serviceLocator() {
        DefaultServiceLocator locator =
                MavenRepositorySystemUtils.newServiceLocator();
        locator.addService(RepositoryConnectorFactory.class,
                BasicRepositoryConnectorFactory.class);
        locator.addService(
                TransporterFactory.class, FileTransporterFactory.class);
        locator.addService(TransporterFactory.class,
                HttpTransporterFactory.class);
        locator.addService(TransporterFactory.class,
                WagonTransporterFactory.class);
        return locator;
    }

    private static Constructor<ModelResolver> modelResolverCtor() throws ClassNotFoundException, NoSuchMethodException {
        @SuppressWarnings("unchecked") Class<ModelResolver> modelResolverClass =
                (Class<ModelResolver>) Class.forName(
                        "org.apache.maven.repository.internal.DefaultModelResolver");
        Constructor<ModelResolver> c =
                modelResolverClass.getDeclaredConstructor(
                        RepositorySystemSession.class, RequestTrace.class,
                        String.class, ArtifactResolver.class,
                        VersionRangeResolver.class,
                        RemoteRepositoryManager.class, List.class);
        c.setAccessible(true);
        return c;
    }

    private static File downloadPOM(String pomURL,
            HttpClient client) throws Exception {
        HttpGet request = new HttpGet(pomURL);
        HttpResponse response = client.execute(request);
        File outputFile = new File(WORKING_DIR + "/" + Paths.get(
                new URI(pomURL).getPath()).getFileName().toString());
        System.out.println("**** " + outputFile.getAbsolutePath());
        try (InputStream contentStream = response.getEntity().getContent()) {
            Files.copy(contentStream, outputFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        return outputFile;
    }
}
