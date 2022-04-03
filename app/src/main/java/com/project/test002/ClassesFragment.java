package com.project.test002;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClassesFragment extends Fragment {

    private RecyclerView classesView;
    private TextView className,classSubject,classSection,classRoom;
    private List<ClassesModel> classesModelList;


    //public ClassesFragment(){
        // This is an Empty constructor
   // }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_classes, container, false);




        Button btnCreateClassFragment = (Button) view.findViewById(R.id.createClassBtn);
        btnCreateClassFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new CreateClassFragment());
                fr.commit();
            }
        });



        classesView = (RecyclerView) view.findViewById(R.id.classes_recyclerview) ;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        classesView.setLayoutManager(layoutManager);


        loadClassesData();

        ClassesAdapter adapter = new ClassesAdapter(classesModelList);
        classesView.setAdapter(adapter);



        initViews(view);
        return view;
    }

    private void loadClassesData() {
        classesModelList = new ArrayList<>();
        classesModelList.add(new ClassesModel("bscs","programming","A","02"));
        classesModelList.add(new ClassesModel("bscs","data structures and algorithm","A","02"));
        classesModelList.add(new ClassesModel("bscs","cyber security","A","02"));
        classesModelList.add(new ClassesModel("bscs","software engineering","A","02"));
    }

    private void initViews(View view) {
        className = (TextView)view.findViewById(R.id.etClassName);
        classSubject = (TextView)view.findViewById(R.id.etSubject);
        classSection=(TextView)view.findViewById(R.id.etSection);
        classRoom = (TextView)view.findViewById(R.id.etRoom);

    }


}
