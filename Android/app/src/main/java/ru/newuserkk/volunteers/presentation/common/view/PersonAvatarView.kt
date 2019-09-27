package ru.newuserkk.volunteers.presentation.common.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ViewSwitcher
import com.github.ivbaranov.mli.MaterialLetterIcon
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import ru.newuserkk.volunteers.domain.person.model.Person
import ru.newuserkk.volunteers.presentation.common.switchTo


class PersonAvatarView : ViewSwitcher {

    var person: Person? = null
        set(value) {
            requireNotNull(value) { "Person cannot be null!" }

            field = value

            if (hasAvatar()) {
                switchToAvatarIcon(value)
            } else {
                switchToLetterIcon(value)
            }
        }


    private var mLetterIcon: MaterialLetterIcon
    private var mAvatar: CircleImageView

    constructor(context: Context) : super(context) {
        mLetterIcon = MaterialLetterIcon(context)
        mAvatar = CircleImageView(context)
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mLetterIcon = MaterialLetterIcon(context, attrs)
        mAvatar = CircleImageView(context, attrs)
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        mLetterIcon = MaterialLetterIcon(context, attrs, defStyleAttr)
        mAvatar = CircleImageView(context, attrs, defStyleAttr)
        setup()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs) {
        mLetterIcon = MaterialLetterIcon(context, attrs, defStyleAttr, defStyleRes)
        mAvatar = CircleImageView(context, attrs, defStyleAttr)
        setup()
    }

    private fun setup() {
        addView(mLetterIcon)
        addView(mAvatar)
    }

    private fun switchToAvatarIcon(value: Person) {
        switchTo(mAvatar)
        Picasso.get()
            .load(value.avatarUri)
            .into(mAvatar, object : Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    switchToLetterIcon(value)
                }
            })
    }

    private fun switchToLetterIcon(value: Person) {
        switchTo(mLetterIcon)
        mLetterIcon.letter = value.name
    }

    fun hasAvatar(): Boolean {
        return person?.avatarUri != null
    }
}