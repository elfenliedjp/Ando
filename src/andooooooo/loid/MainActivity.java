package andooooooo.loid;

import ando.helper.DatabaseHelper;
import android.app.Activity;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button)findViewById(R.id.intent_change_activity);
		button.setText("てってれ〜");
		
		String dbName = getString(R.string.db_name);
		int dbVersion = Integer.parseInt(getString(R.string.db_ver));
		
		// DB構築
		DatabaseHelper helper = new DatabaseHelper(this, dbName, null, dbVersion);
		final SQLiteDatabase db = helper.getReadableDatabase();
		
		// クリック時
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// クリック時の挙動
				Log.d("debug", "てってれ〜");
				Toast.makeText(MainActivity.this, "てってれ〜を押したよ", Toast.LENGTH_SHORT).show();
				db.beginTransaction();
				long recodeCount = DatabaseUtils.queryNumEntries(db, "ando_test"); 
				Log.d("DB", "recode : " + recodeCount);
				Log.d("DB", "SQL : insert ando_test values(" + recodeCount + "子さん" + ")");
				try {
					SQLiteStatement statement = db.compileStatement("insert into ando_test(name) values(?);");
					statement.bindString(1, recodeCount + "子さん");
					statement.executeInsert();
					db.setTransactionSuccessful();
				} finally {
					db.endTransaction();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
