package com.basaki.misc;


import java.lang.management.ManagementFactory;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

/**
 * {@code LoadCpuSolve} calculates the value of CPU load during the execution of
 * a method.
 * <p>
 * Answers to stackoverflow questions:
 * <ol>
 * <li>https://stackoverflow.com/questions/46867363/how-to-get-value-of-load-cpu-during-the-execution-of-the-method/46867865#46867865</li>
 * </ol>
 *
 * @author Indra Basak
 * @since 10/14/17
 */
@SuppressWarnings({"squid:CommentedOutCodeLine", "squid:S106", "squid:S2142", "squid:S1148"})
public class LoadCpuSolve {

    static void complexСalculation() {
        // Complexs calculations here...
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(MBeanServer server, ObjectName name,
            String attrName) throws ReflectionException, InstanceNotFoundException {
        AttributeList attrs =
                server.getAttributes(name, new String[]{attrName});

        Object value = 0;
        if (!attrs.isEmpty()) {
            Attribute att = (Attribute) attrs.get(0);
            value = att.getValue();
        }

        return value;
    }

    public static void main(
            String... args) throws MalformedObjectNameException, ReflectionException, InstanceNotFoundException {
        // boolean condn = true;
        //        OperatingSystemMXBean
        //                mbean = ManagementFactory.getOperatingSystemMXBean();

        //List<OperatingSystemMXBean>
        //        bean = ManagementFactory.getPlatformMXBeans(OperatingSystemMXBean.class);
        // Start complex calculations
        //while(condn) {
        complexСalculation();

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName
                name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        System.out.println(name.getClass());

        System.out.println(
                "While method execute, process CPU load on " + getValue(server,
                        name,
                        "ProcessCpuLoad"));
        System.out.println(
                "While method execute, process CPU time on " + getValue(server,
                        name,
                        "ProcessCpuTime"));
        System.out.println(
                "While method execute, system CPU load on " + getValue(server,
                        name,
                        "SystemCpuLoad"));
    }
}