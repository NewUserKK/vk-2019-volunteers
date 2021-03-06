package ru.wa285.volunteers.presentation.common.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ViewSwitcher
import com.github.ivbaranov.mli.MaterialLetterIcon
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.presentation.common.switchTo

data class NamePicture(val name: String, val avatarUri: String?)

class AvatarView : ViewSwitcher {

    var value: NamePicture? = null
        set(value) {
            requireNotNull(value) { "Value cannot be null!" }

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

    private fun switchToAvatarIcon(value: NamePicture) {
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

    private fun switchToLetterIcon(value: NamePicture) {
        switchTo(mLetterIcon)
        mLetterIcon.letter = value.name
    }

    fun hasAvatar(): Boolean {
        return value?.avatarUri != null
    }
}