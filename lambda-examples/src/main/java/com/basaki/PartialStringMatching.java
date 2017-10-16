package com.basaki;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * {@code PartialStringMatching} is an example of partial String matching using
 * Java8 Streams.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/14/17
 */
@SuppressWarnings({"squid:S106", "squid:S3923"})
public class PartialStringMatching {

    @Data
    @AllArgsConstructor
    public static class ScripInfo {
        private String symbol;

        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class TransactionData {
        private String name;

        private String letter;

        private int number;
    }


    public static void main(String... args) {
        List<ScripInfo> scripInfo = new ArrayList<>();
        scripInfo.add(new ScripInfo("BHARTIARTL", "Bharti Airtel Limited"));
        scripInfo.add(
                new ScripInfo("BHEL", "Bharat Heavy Electricals Limited"));
        scripInfo.add(new ScripInfo("CANBK", "Canara Bank"));
        scripInfo.add(
                new ScripInfo("HINDUNILVR", "Hindustan Unilever Limited"));
        scripInfo.add(new ScripInfo("MARUTI", "Maruti Suzuki India Limited"));
        scripInfo.add(new ScripInfo("TATAPOWER", "Tata Power Company Limited"));
        scripInfo.add(new ScripInfo("TATASTEEL", "Tata Steel Limited"));
        scripInfo.add(new ScripInfo("TECHM", "Tech Mahindra Limited"));

        List<TransactionData> transactionData = new ArrayList<>();
        transactionData.add(new TransactionData("CANARA BANK", "B", 100));
        transactionData.add(new TransactionData("BHARTI AIRTEL LTD", "B", 50));
        transactionData.add(new TransactionData("BHARTI AIRTEL LTD", "B", 20));
        transactionData.add(new TransactionData("HIND.UNILEVER LTD.", "B", 12));
        transactionData.add(new TransactionData("HIND.UNILEVER LTD.", "B", 32));
        transactionData.add(
                new TransactionData("MARUTI SUZUKI INDIA LTD.", "S", 26));
        transactionData.add(
                new TransactionData("MARUTI SUZUKI INDIA LTD.", "S", 26));
        transactionData.add(
                new TransactionData("TECHM FUT 28AUG 14", "S", 125));
        transactionData.add(
                new TransactionData("TECHM FUT 28AUG 14", "B", 125));
        transactionData.add(new TransactionData("TATA STEEL LTD.", "B", 50));
        transactionData.add(
                new TransactionData("TATA POWER CO. LTD.", "B", 100));


        List<TransactionData> filteredData =
                transactionData.stream().filter(t ->
                {
                    List<ScripInfo> filteredScrip =
                            scripInfo.stream().filter(s -> {
                                String[] tranTokens =
                                        t.getName().toUpperCase().split(
                                                " |\\.");
                                String[] scripTokens =
                                        s.getName().toUpperCase().split(" ");
                                String scripSysmbol =
                                        s.getSymbol().toUpperCase();
                                if (tranTokens[0].contains(scripTokens[0])) {
                                    return true;
                                } else if (scripSysmbol.contains(
                                        tranTokens[0])) {
                                    return true;
                                }
                                return false;
                            }).collect(Collectors.toList());

                    return filteredScrip.isEmpty();

                }).collect(Collectors.toList());

        filteredData.forEach(System.out::println);


    }
}
