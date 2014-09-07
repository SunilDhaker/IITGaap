package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 04-09-2014.
 */
public class About extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.about_us, container, false);

        return view;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
