package com.cs371mproject.ambientnights;

import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs371mproject.ambientnights.content.Menu;

/**
 * A fragment representing a single Theme detail screen. This fragment is either
 * contained in a {@link ThemeListActivity} in two-pane mode (on tablets) or a
 * {@link ThemeDetailActivity} on handsets.
 */
public class ThemeDetailFragment extends Fragment {
	Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Menu.Theme mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ThemeDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = Menu.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_theme_detail,
				container, false);

		// if (mItem != null) {
		// // Show the name of the theme
		// ((TextView) rootView.findViewById(R.id.theme_detail))
		// .setText(mItem.content);
		//
		// // Show theme image (To be changed to description)
		// ImageView background = (ImageView)
		// rootView.findViewById(R.id.themeBackground);
		// int id =
		// getResources().getIdentifier("com.cs371mproject.ambientnights:drawable/"
		// + mItem.toString().toLowerCase(Locale.ENGLISH), null, null);
		// background.setImageResource(id);
		//
		// //
		// background.setImageBitmap(decodeSampledBitmapFromResource(getResources(),
		// id, 800, 800));
		// }

		if (mItem != null) {
			// Show the name of the theme
			((TextView) rootView.findViewById(R.id.theme_detail))
					.setText(mItem.content);

			map.put("Jungle", 1);
			map.put("Ocean", 2);
			map.put("Rain", 3);
			map.put("River", 4);

			switch (map.get(mItem.toString())) {
			case 1:
				((TextView) rootView.findViewById(R.id.themeDescription))
				.setText(getResources().getString(R.string.jungle_desc));
				break;
			case 2:
				((TextView) rootView.findViewById(R.id.themeDescription))
				.setText(getResources().getString(R.string.ocean_desc));
				break;
			case 3:
				((TextView) rootView.findViewById(R.id.themeDescription))
				.setText(getResources().getString(R.string.rain_desc));
				break;
			case 4:
				((TextView) rootView.findViewById(R.id.themeDescription))
				.setText(getResources().getString(R.string.river_desc));
				break;
			default:
				((TextView) rootView.findViewById(R.id.themeDescription))
						.setText(getResources().getString(R.string.dne_desc));
				break;
			}
		}

		return rootView;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}
}
