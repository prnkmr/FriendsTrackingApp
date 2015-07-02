package in.littleo.praveenkumar.test3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends ActionBarActivity {
    String username;
    String password;
    String baseurl="http://prn.t15.org/tracker/";
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=(Button)findViewById(R.id.loginButton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onCClick(View v) {
        loginButton.setEnabled(false);
        username=((EditText)findViewById(R.id.editText)).getText().toString();
        password=((EditText)findViewById(R.id.editText2)).getText().toString();
        String url=baseurl+"loginCheck.php";
        List<NameValuePair> list=new ArrayList<NameValuePair>();
        System.out.println(username + " " + password);
        list.add(new BasicNameValuePair("username", username));
        list.add(new BasicNameValuePair("password", password));
        AsyncHttp obj=new AsyncHttp(this,url,list);
        System.out.println("Object Created");
        obj.execute();

    }
    public void callback(String response){
        System.out.println(response);
        String [] msg=response.split("`");
        if("success".equals(msg[0])){
            Intent mainActivity=new Intent(this,MainActivity.class);
            mainActivity.putExtra("userid",msg[1]);
            startActivity(mainActivity);
            this.finish();
        }else if("fail".equals(msg[0])){
            loginButton.setEnabled(true);
            Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Unknown Error",Toast.LENGTH_SHORT).show();
        }
    }
}
