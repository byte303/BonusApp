package frog.company.bonusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import frog.company.bonusapp.enums.EnumStatusUser
import frog.company.bonusapp.enums.EnumTypePin
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.User
import frog.company.bonusapp.ui.registration.RegistrationFragment
import frog.company.bonusapp.ui.registration.SetPinCodeFragment
import io.paperdb.Paper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(this)
        val user : User? = Paper.book().read<User?>(AppConst.PAPER_DATA, null)
        val pinCode = Paper.book().read(AppConst.PINCODE, "")
        if(user != null){
            if(user.status == EnumStatusUser.NOT_ACTIVE.value){
                supportFragmentManager.beginTransaction().replace(
                    R.id.viewPager,
                    RegistrationFragment()
                ).commit()
            }else{
                if(pinCode == ""){
                    val fragment = SetPinCodeFragment.newInstance(EnumTypePin.CREATE.value)
                    supportFragmentManager.beginTransaction().replace(
                        R.id.viewPager,
                        fragment
                    ).commit()
                }else{
                    val fragment = SetPinCodeFragment.newInstance(EnumTypePin.LOGIN.value)
                    supportFragmentManager.beginTransaction().replace(
                        R.id.viewPager,
                        fragment
                    ).commit()
                }
            }
        }else{
            supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                RegistrationFragment()
            ).commit()
        }

    }
}