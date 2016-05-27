package johnkriegerinc.threading;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Threading extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threading);
    }


    public void createAction(View view) {
        //Context context = null;

        String filename = "numbers.txt";
        File file = new File(getFilesDir(), filename);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for (int i = 1; i <= 10; i++) {
                outputStream.write((Integer.toString(i).getBytes()));
                outputStream.write("\n".getBytes());
                Thread.sleep(250);
            }
            outputStream.close();

        } catch (Exception e) {
            System.out.println("Error exception caught");
        }

    }

    public ArrayAdapter<String> loadAction(View view) {


        FileInputStream inputStream = null;
        try {
            inputStream = openFileInput("numbers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        List<String> numberList = new ArrayList<String>();
        String numbers;

        try {
            while ((numbers = bufferedReader.readLine()) != null) {
                numberList.add(numbers);
                Thread.sleep(250);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numberList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        return adapter;
    }

    public void clearAction(View view) {
        ArrayAdapter<String> adapter = loadAction(view);
        adapter.clear();

    }


}



