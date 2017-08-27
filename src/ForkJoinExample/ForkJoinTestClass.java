package ForkJoinExample;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by igaurav on 5/25/2017.
 */
public class ForkJoinTestClass {

    public  static  void main(String args[]){
        ForkJoinPool pool = new ForkJoinPool();
        SearchDirectory searchDirectory = new SearchDirectory("C:/Users/igaurav/Desktop/Important things","txt");
        pool.execute(searchDirectory);
        List<String> fileList = searchDirectory.join();
        System.out.println("The Search returned following files :"+ fileList);
    }
}
