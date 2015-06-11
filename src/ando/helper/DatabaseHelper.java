package ando.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	/**
	 * コンストラクタ
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
    /** 
     * onCreateメソッド 
     * データベースが作成された時に呼ばれます。 
     * テーブルの作成などを行います。 
     */ 
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "";  
        sql += "create table ando_test(";  
        sql += " No integer primary key autoincrement";  
        sql += ",Name text not null";
        sql += ")";  
        db.execSQL(sql);
	}

    /**
     * onUpgradeメソッド 
     * onUpgrade()メソッドはデータベースをバージョンアップした時に呼ばれます。 
     * 現在のレコードを退避し、テーブルを再作成した後、退避したレコードを戻すなどの処理を行います。 
     */ 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
