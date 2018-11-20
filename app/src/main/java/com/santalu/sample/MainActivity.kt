package com.santalu.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.pager
import kotlinx.android.synthetic.main.fragment_main.text

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    pager.apply {
      adapter = SampleAdapter(supportFragmentManager)
      //autoScroll = true
      //indeterminate = true
    }
  }

  class SampleAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
      return SampleFragment.newInstance(position)
    }

    override fun getCount(): Int {
      return 3
    }
  }

  class SampleFragment : Fragment() {

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
      return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
      super.onActivityCreated(savedInstanceState)
      arguments?.let {
        val position = it.getInt(ARG_POSITION)
        text.text = getString(R.string.page, position)
      }
    }

    companion object {
      const val ARG_POSITION = "position"

      fun newInstance(position: Int): SampleFragment {
        return SampleFragment().apply {
          arguments = Bundle().apply { putInt(ARG_POSITION, position) }
        }
      }
    }
  }
}
