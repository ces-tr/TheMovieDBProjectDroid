package com.cestr.themoviedb.utils

class ImageUrlProvider {

    companion object {
//        private val XLARGE_SIZE_PX = 2048
//        private val LARGE_SIZE_PX = 1440
//        private val MEDIUM_LARGE_SIZE_PX = 1125
        private const val MEDIUM_SIZE_PX = 780
        private const val MEDIUM_SMALL_SIZE_PX = 500
        private const val SMALL_SIZE_PX = 342
        private const val SMALL_LOW_SIZE_PX = 185
        private const val THUMB_SIZE_PX = 154
        private const val ICON_SIZE_PX = 92

        private val ORIGINAL_SIZE_PX = Int.MAX_VALUE
        private val SIZEPREFIX = "w"

        private val SIZES_PX: IntArray
        private val ORIGINAL_SIZE = "original"
        private val SLASH = "/"



        init {

            SIZES_PX = intArrayOf(ICON_SIZE_PX, THUMB_SIZE_PX, SMALL_LOW_SIZE_PX, SMALL_SIZE_PX, MEDIUM_SMALL_SIZE_PX, MEDIUM_SIZE_PX, ORIGINAL_SIZE_PX)
        }

        private val baseUrl: String
            get() = "http://image.tmdb.org/t/p/"

        private fun getCompleteImageUrl(size: String, imageName: String): String {
            val stringBuilder = StringBuilder()
            stringBuilder.append(ImageUrlProvider.baseUrl)
            stringBuilder.append(size)
            stringBuilder.append(imageName)
            return stringBuilder.toString()
        }

        fun getUrlForSize(n: Int, string: String): String {
            return ImageUrlProvider.getCompleteImageUrl(
                ImageUrlProvider.obtainSizeString(ImageUrlProvider.obtainSizeFor(n)),
                string
            )
        }

        private fun obtainSizeFor(size: Int): Int {

            val arrn = SIZES_PX
            val pxsizeslength = arrn.size
            var sizeindex = pxsizeslength-1
            var bestSize:Int = 185

            while (sizeindex < pxsizeslength && sizeindex>=0) {
                val currsize = arrn[sizeindex]

                if (size <= currsize) {
                    bestSize =currsize
                }else{
                    break
                }
                sizeindex--
            }

            return bestSize
        }

        private fun obtainSizeString(sizerequest: Int): String {

            val strsize= sizerequest.toString()
            val stringBuilder = StringBuilder()

            if (sizerequest == Int.MAX_VALUE) {
                stringBuilder.append(ORIGINAL_SIZE)
            }
            else {
                stringBuilder.append(SIZEPREFIX)
                stringBuilder.append(strsize)
            }

            stringBuilder.append(SLASH)
            return stringBuilder.toString()
        }


    }
}