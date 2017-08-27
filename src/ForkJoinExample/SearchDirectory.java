package ForkJoinExample;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by igaurav on 5/25/2017.
 */
public class SearchDirectory extends RecursiveTask<List<String>> {

    private String directoryName="";

    private String searchString="";

     SearchDirectory(String directoryName, String searchString){
        this.directoryName=directoryName;
        this.searchString=searchString;
    }

    @Override
    protected List<String> compute() {
        List<String> matchingFileList = new ArrayList<String>();
        List<SearchDirectory> taskList = new ArrayList<SearchDirectory>();
        File directory = new File(directoryName);

        if(directoryName==null || "".equals(directoryName) || !directory.exists()){
            throw  new IllegalArgumentException("Directory Name is not valid");
        }

        File[] fileArray = directory.listFiles();
        for(File file: fileArray){
            if(file.isDirectory()){
                SearchDirectory searchDirectory = new SearchDirectory(directoryName,searchString);
                searchDirectory.fork();
                taskList.add(searchDirectory);
            }
            else{
                if(checkName(file.getName())){
                    matchingFileList.add(file.getPath());
                }
            }
        }

        for(SearchDirectory sd : taskList){
            List<String> intermediateResultList = sd.join();
            intermediateResultList.addAll(intermediateResultList);
        }

        return matchingFileList;
    }

    private boolean checkName(String fileName){
        return fileName.contains(searchString);
    }

}
