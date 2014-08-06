package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 06-08-2014.
 */
public class Departments extends Fragment implements ListView.OnItemClickListener {

    DepartmentsListAdapter mDepartmentAdapter;
    ListView mDepartmentListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.hosel_list, container, false);
        mDepartmentListView = (ListView) view.findViewById(R.id.hostel_list);
        mDepartmentListView.setAdapter(mDepartmentAdapter);
        mDepartmentListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mDepartmentAdapter = new DepartmentsListAdapter(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Activity activity = getActivity();
        Intent i = new Intent(activity, Department.class);
        i.putExtra("departmentID", position);
        startActivity(i);
    }


}
