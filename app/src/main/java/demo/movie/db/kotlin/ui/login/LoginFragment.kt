package demo.movie.db.kotlin.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import demo.movie.db.kotlin.R
import demo.movie.db.kotlin.databinding.FragmentMoviedbLoginBinding
import demo.movie.db.kotlin.utils.EmailValidator
import demo.movie.db.kotlin.utils.SharedPreferencesHelper
import demo.movie.db.kotlin.utils.SharedPreferencesKey.FIRST_TIME
import demo.movie.db.kotlin.utils.SharedPreferencesKey.LOGIN_USEREMAIL

class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var bindingView: FragmentMoviedbLoginBinding
    private lateinit var sharedPreferences: SharedPreferencesHelper
    private var mEmailValidator: EmailValidator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingView = FragmentMoviedbLoginBinding.inflate(layoutInflater, container, false)
        sharedPreferences = SharedPreferencesHelper(requireContext())
        mEmailValidator = EmailValidator()
        bindingView.inputEmailId.addTextChangedListener(mEmailValidator)

        if (sharedPreferences.get(FIRST_TIME, false)!!) {
            findNavController().navigate(R.id.movie_home)
        } else {
            sharedPreferences.put(LOGIN_USEREMAIL, "test@test.com")
        }

        bindingView.loginButton.setOnClickListener(this)
        return bindingView.root
    }

    override fun onClick(clickedView: View) {
        when (clickedView.id) {
            R.id.login_button -> {
                if (!mEmailValidator!!.isValid) {
                    bindingView.inputEmailId.error = "Invalid email"
                    return
                }
                val user_email = bindingView.inputEmailId.text.toString()
                val password = bindingView.inputPassword.text.toString()

                if (user_email.equals(
                        sharedPreferences.get(
                            LOGIN_USEREMAIL,
                            ""
                        )
                    ) && password.equals("123456")
                ) {
                    findNavController().navigate(R.id.movie_home)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.user_email_password_is_wrong),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
