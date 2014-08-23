package sunil.dhaker.iitgnotif.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sunil.dhaker.iitgnotif.R;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 22-08-2014.
 */
public class NavigationDrawerAdapter extends BaseAdapter {
    Context context;
    String[] choices;

    public NavigationDrawerAdapter(Context context) {
        this.context = context;
        choices = new String[]{
                context.getString(R.string.title_myfeed),
                context.getString(R.string.title_hoestels),
                context.getString(R.string.title_department),
                context.getString(R.string.title_club),
                context.getString(R.string.lost_and_found),
                context.getString(R.string.bus_tracking),
                context.getString(R.string.register),
                context.getString(R.string.about_us)
        };
    }

    public static Bitmap GetBitmapClippedCircle(Bitmap bitmap) {

        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final Bitmap outputBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);

        final Path path = new Path();
        path.addCircle((float) (width / 2), (float) (height / 2),
                (float) Math.min(width, (height / 2) - 5.0), Path.Direction.CCW);

        final Canvas canvas = new Canvas(outputBitmap);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 0, 0, null);
        return outputBitmap;
    }

    @Override
    public int getCount() {
        return choices.length;
    }

    @Override
    public Object getItem(int position) {
        return choices[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.navigation_drawer_element, parent, false);
        TextView choiceText = (TextView) v.findViewById(R.id.choice_text);
        choiceText.setText(choices[position]);
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_menu_set_as);
        logo = GetBitmapClippedCircle(logo);
        ImageView choiceImage = (ImageView) v.findViewById(R.id.choice_image);
        choiceImage.setImageBitmap(logo);
        return v;
    }
}

