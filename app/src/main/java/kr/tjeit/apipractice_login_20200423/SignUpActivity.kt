package kr.tjeit.apipractice_login_20200423

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.tjeit.apipractice_login_20200423.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        signUpBtn.setOnClickListener {
            val id = loginIdEdt.text.toString()
            val pw = pwEdt.text.toString()
            val name = nameEdt.text.toString()
            val phoneNum = phoneNumEdt.text.toString()

            ServerUtil.putRequestSignUp(mContext, id, pw, name, phoneNum, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    Log.d("회원가입응답", json.toString())

                    val code = json.getInt("code")

                    if (code == 200) {
                        runOnUiThread {
                            Toast.makeText(mContext, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                            finish()
                        }

                    }
                    else {

//                        서버에서 내려주는 메세지를 토스트로 출력
//                        cho881020 아이디 / asdf 아이디로 가입.
//                        "" 으로 가입하려고 하면 빈값 X.

                        val message = json.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })

        }

    }

    override fun setValues() {

    }

}
