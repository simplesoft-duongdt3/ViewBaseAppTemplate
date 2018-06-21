package viewbase.app.demo.com.viewbaseapp.base.listview.view

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by leducanh on 20/12/2017.
 */
class RvItemClickSupport {
    private var mRecyclerView: RecyclerView? = null
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null
    private var mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
        mRecyclerView?.let {
            if (mOnItemClickListener != null) {
                val holder = it.getChildViewHolder(v)
                val rv = it
                holder?.let {
                    val position = it.adapterPosition
                    mOnItemClickListener?.onItemClicked(rv, position, v)
                }
            }
        }
    }

    private var mOnLongClickListener: View.OnLongClickListener = View.OnLongClickListener { v ->
        mRecyclerView?.let { rv ->
            mOnItemLongClickListener?.let { onItemLongClickListener ->
                mRecyclerView?.let {
                    val holder: RecyclerView.ViewHolder = it.getChildViewHolder(v)
                    onItemLongClickListener.onItemLongClicked(rv, holder.adapterPosition, v)
                }
            }
        }
        false
    }

    private var mAttachListener: RecyclerView.OnChildAttachStateChangeListener = object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View?) {

        }

        override fun onChildViewAttachedToWindow(view: View?) {
            view?.let {
                if (mOnItemClickListener != null) {
                    if (!it.hasOnClickListeners()) {
                        it.setOnClickListener(mOnClickListener)
                    }
                }
                if (mOnItemLongClickListener != null) {
                    it.setOnLongClickListener(mOnLongClickListener)
                }
            }
        }
    }

    constructor(recyclerView: RecyclerView) {
        this.mRecyclerView = recyclerView
        this.mRecyclerView?.addOnChildAttachStateChangeListener(mAttachListener)
    }

    companion object {
        @JvmStatic
        fun addTo(rv: RecyclerView): RvItemClickSupport {
            return RvItemClickSupport(rv)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener): RvItemClickSupport {
        this.mOnItemClickListener = listener
        return this
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener): RvItemClickSupport {
        this.mOnItemLongClickListener = listener
        return this
    }

    fun detach(rv: RecyclerView) {
        rv.removeOnChildAttachStateChangeListener(mAttachListener)
    }

    interface OnItemClickListener {
        fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View?)
    }

    interface OnItemLongClickListener {
        fun onItemLongClicked(recyclerView: RecyclerView, position: Int, v: View?): Boolean
    }
}