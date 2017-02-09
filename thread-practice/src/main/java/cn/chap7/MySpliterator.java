package cn.chap7;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by hjy on 17-2-8.
 */
public class MySpliterator {

    public static int countWOrdsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for (char c :
                s.toCharArray()) {
            if (Character.isWhitespace(c)){
                lastSpace = true;
            }else {
                if (lastSpace) counter++;
                lastSpace=false;
            }
        }

        return counter;
    }

    private  int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0,true),WordCounter::accumulate,WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
        final String SENTENCE = "Nel mezzo del cammin di nostra vita "+ "mi ritrovai in una selva oscura " + "che la dritta via era smarrita";

        System.out.println("Found " + countWOrdsIteratively(SENTENCE) + "  words");

        Stream<Character> stream = IntStream.range(0,SENTENCE.length()).mapToObj(SENTENCE::charAt);

        System.out.println("-------Found " + new MySpliterator().countWords(stream.parallel()) + "  words");

       /* Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator,true);

        System.out.println("-------Found " + new MySpliterator().countWords(stream1.parallel()) + "  words");*/
    }



    class WordCounter{
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c){
            if (Character.isWhitespace(c)){
                return lastSpace?this:new WordCounter(counter,true);
            }else {
                return lastSpace?new WordCounter(counter+1,false):this;
            }
        }

        public WordCounter combine(WordCounter wordCounter){
            return new WordCounter(counter+ wordCounter.counter,wordCounter.lastSpace);
        }

        public int getCounter(){
            return counter;
        }

    }



    class WordCounterSpliterator implements Spliterator<Character>{

        private final String string;
        private int currentChar = 0;

        public WordCounterSpliterator(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));    //处理当前字符
            return currentChar < string.length(); //如果还有字符要吹，则返回rue;
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length()-currentChar;
            if (currentSize<10){
                return null;
            }
            for (int splitPos = currentSize/2 + currentChar;splitPos<string.length();splitPos++){
                if (Character.isWhitespace(string.charAt(splitPos))){
                    Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentSize,splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length()-currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED +NONNULL + IMMUTABLE;
        }
    }
}
