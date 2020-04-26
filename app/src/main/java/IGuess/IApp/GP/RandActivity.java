package IGuess.IApp.GP;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import java.util.*;
import java.text.*;


public class RandActivity extends Activity {

	private LinearLayout top;
	private LinearLayout bg;
	private LinearLayout top_name;
	private TextView descr;
	private TextView name_txt;
	private TextView iapp_txt;
	private LinearLayout bg_inner;
	private LinearLayout btns_bg;
	private LinearLayout bg_ad;
	private TextView head_txt;
	private LinearLayout nn;
	private LinearLayout bg_inner_home;
	private LinearLayout wt_bg;
	private TextView view_rnd;
	private TextView plswat_txt;
	private TextView timer;
	private TextView sec_txt;
	private TextView frm_txt;
	private EditText rnd_1;
	private TextView to_txt;
	private EditText rnd_2;
	private Button rnd_btn;
	private Button sug_btn;
	private Button about_btn;
	private Button moreapps_btn;
	private WebView ad;
	private Button bk_btn;

	private double timerr = 0;
	private double r1 = 0;
	private double r2 = 0;
	private String r1_str = "";
	private String r2_str = "";


	private Timer _timer = new Timer();
	private Intent go = new Intent();
	private TimerTask timee;
	private AlertDialog.Builder dialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rand);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		top = (LinearLayout) findViewById(R.id.top);
		bg = (LinearLayout) findViewById(R.id.bg);
		top_name = (LinearLayout) findViewById(R.id.top_name);
		descr = (TextView) findViewById(R.id.descr);
		name_txt = (TextView) findViewById(R.id.name_txt);
		iapp_txt = (TextView) findViewById(R.id.iapp_txt);
		bg_inner = (LinearLayout) findViewById(R.id.bg_inner);
		btns_bg = (LinearLayout) findViewById(R.id.btns_bg);
		bg_ad = (LinearLayout) findViewById(R.id.bg_ad);
		head_txt = (TextView) findViewById(R.id.head_txt);
		nn = (LinearLayout) findViewById(R.id.nn);
		bg_inner_home = (LinearLayout) findViewById(R.id.bg_inner_home);
		wt_bg = (LinearLayout) findViewById(R.id.wt_bg);
		view_rnd = (TextView) findViewById(R.id.view_rnd);
		plswat_txt = (TextView) findViewById(R.id.plswat_txt);
		timer = (TextView) findViewById(R.id.timer);
		sec_txt = (TextView) findViewById(R.id.sec_txt);
		frm_txt = (TextView) findViewById(R.id.frm_txt);
		rnd_1 = (EditText) findViewById(R.id.rnd_1);
		to_txt = (TextView) findViewById(R.id.to_txt);
		rnd_2 = (EditText) findViewById(R.id.rnd_2);
		rnd_btn = (Button) findViewById(R.id.rnd_btn);
		sug_btn = (Button) findViewById(R.id.sug_btn);
		about_btn = (Button) findViewById(R.id.about_btn);
		moreapps_btn = (Button) findViewById(R.id.moreapps_btn);
		ad = (WebView) findViewById(R.id.ad);
		ad.getSettings().setJavaScriptEnabled(true);
		ad.getSettings().setSupportZoom(true);
		ad.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageStarted(WebView _view, String _url, Bitmap _favicon) {

					super.onPageStarted(_view, _url, _favicon);
				}
				@Override
				public void onPageFinished(WebView _view, String _url) {

					super.onPageFinished(_view, _url);
				}
			});
		bk_btn = (Button) findViewById(R.id.bk_btn);



		dialog = new AlertDialog.Builder(this);

		rnd_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					nn.setEnabled(true);
					nn.setVisibility(View.VISIBLE);
					view_rnd.setVisibility(View.INVISIBLE);
					bg_inner_home.setVisibility(View.INVISIBLE);
					bg_inner_home.setEnabled(false);
					bk_btn.setVisibility(View.INVISIBLE);
					bk_btn.setEnabled(false);
					showMessage("Please Wait 5 Seconds...");
					timee = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
										timerr--;
										timer.setText(String.valueOf((long)(timerr)));
									}
								});
						}
					};
					_timer.scheduleAtFixedRate(timee, (int)(1), (int)(1000));
					r1 = Double.parseDouble(r1_str);
					r2 = Double.parseDouble(r2_str);
					timee = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
										timee.cancel();
										bk_btn.setEnabled(true);
										bk_btn.setVisibility(View.VISIBLE);
										plswat_txt.setVisibility(View.INVISIBLE);
										plswat_txt.setEnabled(false);
										timer.setVisibility(View.INVISIBLE);
										timer.setEnabled(false);
										sec_txt.setVisibility(View.INVISIBLE);
										sec_txt.setEnabled(false);
										timerr = 0;
										view_rnd.setVisibility(View.VISIBLE);
										view_rnd.setText(String.valueOf(getRandom((int)(r1), (int)(r2))));
										showMessage("Thanks for Wait.");
									}
								});
						}
					};
					_timer.schedule(timee, (int)(5000));
				}
			});
		sug_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					go.setClass(getApplicationContext(), MainActivity.class);
					startActivity(go);
				}
			});
		about_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					dialog.setTitle("About");
					dialog.setMessage(" © ( IApp ) Powered by GP & PWS & Elgeneral Primo \n\n Site : PWS-Apps.BlogSpot.Com \n\n IGuess V 1.1");
					dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {

							}
						});
					dialog.create().show();
				}
			});
		moreapps_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					go.setAction(Intent.ACTION_VIEW);
					go.setData(Uri.parse("http://pws-apps.blogspot.com/search/?label=IApp"));
					startActivity(go);
				}
			});
		rnd_1.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
				}
				@Override
				public void onTextChanged(CharSequence _text, int _start, int _before, int _count) {
					r1_str = _text.toString();
					go.putExtra("1", r1_str);
				}
				@Override
				public void afterTextChanged(Editable _text) {
				}
			});
		rnd_2.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
				}
				@Override
				public void onTextChanged(CharSequence _text, int _start, int _before, int _count) {
					r2_str = _text.toString();
					go.putExtra("2", _text.toString());
				}
				@Override
				public void afterTextChanged(Editable _text) {
				}
			});
		bk_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					go.setClass(getApplicationContext(), RandActivity.class);
					startActivity(go);
				}
			});

	}

	private void  initializeLogic() {
		nn.setEnabled(false);
		nn.setVisibility(View.INVISIBLE);
		ad.loadUrl("file:///android_asset/mob_ads.html");
		timerr = 5;
		r1 = 0;
		r2 = 0;
	}




	// created automatically
	private void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	private int getRandom(int _minValue ,int _maxValue){
		Random random = new Random();
		return random.nextInt(_maxValue - _minValue + 1) + _minValue;
	}

	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
				_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}

}

