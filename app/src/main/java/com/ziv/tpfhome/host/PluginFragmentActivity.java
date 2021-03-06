//package com.ziv.tpfhome.host;
//
//import android.app.Activity;
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import com.tencent.tws.pluginhost.R;
//import com.tws.plugin.core.PluginLoader;
//import com.tws.plugin.core.annotation.PluginContainer;
//
///**
// * 一个非常普通的FragmentActivty， 用来展示一个来自插件中的fragment。
// * 这里需要通过注解@FragmentContainer来通知插件框架,此activity要展示
// * 的fragment来自那个插件，从而提前更换当前Activity的Context为插件Context
// *
// * @author yongchen
// *
// */
//public class PluginFragmentActivity extends Activity implements PluginContainer {
//
//	public static final String FRAGMENT_ID_IN_PLUGIN = "PluginDispatcher.fragmentId";
//	private static final String LOG_TAG = PluginFragmentActivity.class.getSimpleName();
//	static final String FRAGMENTS_TAG = "android:fragments";
//
//	private String mPluginID = "";
//	private String mFragmentID = "";
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		setContentView(R.layout.activity_plugin_fragment);
//		mPluginID = getIntent().getStringExtra(FRAGMENT_PLUGIN_ID);
//
//		String classId = getIntent().getStringExtra(FRAGMENT_ID_IN_PLUGIN);
//		if (classId == null && savedInstanceState != null) {
//			classId = savedInstanceState.getString(FRAGMENT_ID_IN_PLUGIN);
//		}
//
//		loadPluginFragment(classId);
//	}
//
//	@Override
//	public void setPluginId(String id) {
//		mPluginID = id;
//	}
//
//	@Override
//	public String getPluginId() {
//		return mPluginID;
//	}
//
//	private void loadPluginFragment(String classId) {
//		try {
//			if (classId == null) {
//				Toast.makeText(this, "缺少参数:PluginDispatcher.fragmentId", Toast.LENGTH_SHORT).show();
//				return;
//			}
//			Log.d(LOG_TAG, "loadPluginFragment, classId is " + classId);
//			@SuppressWarnings("rawtypes")
//			Class clazz = PluginLoader.loadPluginFragmentClassById(classId);
//			if (clazz != null) {
//				Fragment fragment = (Fragment) clazz.newInstance();
//				FragmentTransaction ft = getFragmentManager().beginTransaction();
//				ft.replace(R.id.fragment_container, fragment).commit();
//			}
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		if (item.getItemId() == android.R.id.home) {
//			finish();
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//	@Override
//	protected void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
//		if (outState != null) {
//			outState.remove(FRAGMENTS_TAG);
//
//			if (!TextUtils.isEmpty(mFragmentID)) {
//				outState.putString(FRAGMENT_ID_IN_PLUGIN, mFragmentID);
//			}
//		}
//	}
//}
