@file:Suppress("FunctionName")

package com.github.daneko.android.text

import android.content.Context
import android.widget.TextView
import androidx.annotation.StringRes

interface Text {
    fun get(context: Context): String
    operator fun invoke(context: Context) = get(context)
    operator fun plus(other: Text): Text = MergeText(listOf(this, other))
}

// @androidx.databinding.BindingAdapter.BindingAdapter("text")
fun TextView.setText(src: Text?) {
    src ?: return
    this.text = src.get(context)
}

fun Text(@StringRes resId: Int): Text = ResourceText(resId)
fun Text(@StringRes resId: Int, vararg formatArgs: Any): Text =
    ResourceWithArgsText(resId, formatArgs)

fun Text(format: String, vararg formatArgs: Any): Text = StringWithArgsText(format, formatArgs)
fun Text(string: String): Text = StringText(string)
fun Text(f: (Context) -> Text): Text = LazyText(f)

private class ResourceText(@StringRes val resId: Int) : Text {
    override fun get(context: Context): String {
        return context.getString(resId)
    }
}

private class ResourceWithArgsText(
    @StringRes val resId: Int,
    val formatArgs: Array<out Any>
) : Text {
    override fun get(context: Context): String {
        val adjust = formatArgs.map {
            (it as? Text)?.get(context) ?: it
        }.toTypedArray()
        return context.getString(resId, *adjust)
    }
}

private class StringWithArgsText(
    val format: String,
    val formatArgs: Array<out Any>
) : Text {
    override fun get(context: Context): String {
        val adjust = formatArgs.map {
            (it as? Text)?.get(context) ?: it
        }.toTypedArray()
        return String.format(format, *adjust)
    }
}

private class StringText(val string: String) : Text {
    override fun get(context: Context): String {
        return string
    }
}

private class MergeText(val src: List<Text>) : Text {
    override fun get(context: Context): String {
        return src.fold("") { a, b -> a + b.get(context) }
    }

    override fun plus(other: Text): Text {
        return MergeText(src + other)
    }
}

private class LazyText(val f: (Context) -> Text) : Text {
    override fun get(context: Context): String {
        return f(context).get(context)
    }
}
