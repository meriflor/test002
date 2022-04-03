package com.project.test002;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder>{

    private List<ClassesModel> ClassesModelList;

    public ClassesAdapter(List<ClassesModel> classesModelList) {
        ClassesModelList = classesModelList;
    }

    @NonNull
    @Override
    public ClassesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_item_layout,parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesAdapter.ViewHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return ClassesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView className;
        private TextView subjectName;
        private TextView sectionName;
        private TextView classRoom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            className = itemView.findViewById(R.id.etClassName);
            subjectName = itemView.findViewById(R.id.etSubject);
            sectionName = itemView.findViewById(R.id.etSection);
            classRoom = itemView.findViewById(R.id.etRoom);

        }
        private void setData(){

        }
    }
}
