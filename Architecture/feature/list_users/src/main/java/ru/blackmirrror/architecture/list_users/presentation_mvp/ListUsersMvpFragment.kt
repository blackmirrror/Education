package ru.blackmirrror.architecture.list_users.presentation_mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.blackmirrror.architecture.list_users.R
import ru.blackmirrror.architecture.list_users.databinding.FragmentListUsersMvpBinding
import ru.blackmirrror.architecture.list_users.domain.model.User

class ListUsersMvpFragment : Fragment(), ListUsersContract.View {

    private var binging: FragmentListUsersMvpBinding?= null
    private lateinit var presenter: ListUsersContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binging = FragmentListUsersMvpBinding.inflate(inflater, container, false)
        return binging!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getUsers()

        binging?.refreshMvp?.setOnClickListener {
            presenter.getUsers()
        }
    }

    override fun showLoadingState() {
        TODO()
    }

    override fun showErrorState() {
        TODO()
    }

    override fun setListUsers(users: List<User>) {
        TODO()
    }

}