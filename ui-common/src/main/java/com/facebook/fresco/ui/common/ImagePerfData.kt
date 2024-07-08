/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.fresco.ui.common

import com.facebook.common.internal.Objects
import com.facebook.fresco.ui.common.ControllerListener2.Extras

class ImagePerfData(
    val controllerId: String?,
    val requestId: String?,
    val imageRequest: Any?,
    val callerContext: Any?,
    val imageInfo: Any?,
    val controllerSubmitTimeMs: Long,
    val intermediateImageLoadTimeMs: Long,
    val controllerFinalImageSetTimeMs: Long,
    val controllerFailureTimeMs: Long,
    val imageRequestStartTimeMs: Long,
    val imageRequestEndTimeMs: Long,
    val isPrefetch: Boolean,
    val onScreenWidthPx: Int,
    val onScreenHeightPx: Int,
    val errorThrowable: Throwable?,
    // Visibility
    val visibilityState: VisibilityState,
    val visibilityEventTimeMs: Long,
    val invisibilityEventTimeMs: Long,
    val dimensionsInfo: DimensionsInfo?,
    var extraData: Extras?
) {

  val finalImageLoadTimeMs: Long
    get() =
        if (imageRequestEndTimeMs == UNSET.toLong() || imageRequestStartTimeMs == UNSET.toLong()) {
          UNSET.toLong()
        } else {
          imageRequestEndTimeMs - imageRequestStartTimeMs
        }

  fun createDebugString(): String =
      Objects.toStringHelper(this)
          .add("controller ID", controllerId)
          .add("request ID", requestId)
          .add("controller submit", controllerSubmitTimeMs)
          .add("controller final image", controllerFinalImageSetTimeMs)
          .add("controller failure", controllerFailureTimeMs)
          .add("start time", imageRequestStartTimeMs)
          .add("end time", imageRequestEndTimeMs)
          .add("prefetch", isPrefetch)
          .add("caller context", callerContext)
          .add("image request", imageRequest)
          .add("image info", imageInfo)
          .add("on-screen width", onScreenWidthPx)
          .add("on-screen height", onScreenHeightPx)
          .add("visibility state", visibilityState)
          .add("visibility event", visibilityEventTimeMs)
          .add("invisibility event", invisibilityEventTimeMs)
          .add("dimensions info", dimensionsInfo)
          .add("extra data", extraData)
          .toString()

  companion object {
    const val UNSET: Int = -1
  }
}
