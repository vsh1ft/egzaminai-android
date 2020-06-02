package com.project.egzaminai2.activity.frg

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.project.egzaminai2.R
import com.project.egzaminai2.SelectedExamSingleton
import com.project.egzaminai2.Utils.Downloader
import com.project.egzaminai2.Utils.MyUtils
import java.io.File

/**
 * Created by Vsh1ft on 8/14/13.
 */
class PaperFragment: Fragment() {
    private var pdfView: PDFView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frg_paper, container, false)
        val examName = SelectedExamSingleton.paperUrl
        pdfView = view.findViewById<View>(R.id.pdfView) as PDFView
        var dir = activity!!.getDir("Egzaminai", Context.MODE_PRIVATE)
        if (MyUtils.isExternalStorageWritable && MyUtils.isExternalStorageReadable && MyUtils.isExternalStorageAvailable && MyUtils.checkPermission(
                context
            )
        ) {
            dir = File(Environment.getExternalStorageDirectory(), "/Egzaminai/")
            if (!dir.exists()) {
                dir.mkdir()
            }
        }
        val filePath = File(dir.path + "/" + examName)
        if (filePath.exists()) {
            showPDF(filePath)
        } else {
            val downloadFile = Downloader(activity, SelectedExamSingleton.paperUrl)
            downloadFile.setPdfView(pdfView)
            downloadFile.setOpenOneExam(true)
            downloadFile.execute(examName)
        }
        return view
    }

    private fun showPDF(filePath: File) {
        pdfView!!.fromFile(filePath)
            .defaultPage(0)
            .enableDoubletap(true)
            .enableAnnotationRendering(false)
            .password(null)
            .scrollHandle(DefaultScrollHandle(activity))
            .enableSwipe(true)
            .swipeHorizontal(true)
            .load()
    }
}
