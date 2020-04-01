package com.digisky.liuwei2.protobuf;

/**
 * @author liuwei2
 * 2020/2/20 12:17
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Search.SearchRequest.Builder builder = Search.SearchRequest.newBuilder();
        builder.setQuery("query");
        builder.setPageNumber(1);
        builder.setResultPerPage(2);
        builder.setCorpus(EnumContent.Corpus.NEWS);

        Search.SearchRequest request = builder.build();

        System.out.println(request.toByteArray().length);

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("hahahah");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        System.out.println("等待线程t执行完成");
        t.join();
    }
}
