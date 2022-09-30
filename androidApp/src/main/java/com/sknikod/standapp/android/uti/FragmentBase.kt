package com.sknikod.standapp.android.uti

import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.sknikod.standapp.android.R

open class FragmentBase : Fragment(){
    override fun onPause() {
        view?.rootView?.findViewById<MaterialToolbar>(R.id.topAppBar)?.title = " "
        super.onPause()
    }
}
