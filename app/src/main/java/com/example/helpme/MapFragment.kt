package com.example.helpme

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.helpme.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapFragment : Fragment() {

    lateinit var mapView: MapView

    private var _binding: FragmentMapBinding? = null
    private val binding
        get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val eventListener = MarkerEventListener(requireContext())

        mapView = MapView(activity)
        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                37.2325781224618740000000000,
                127.1880270943115500000000000
            ), true
        );

        val marker = MapPOIItem()
        marker.itemName = "청년 다방"
        marker.tag = 1
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(
            37.2325781224618740000000000,
            127.1880270943115500000000000
        )
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        val marker2 = MapPOIItem()
        marker2.itemName = "엽기 떡볶이"
        marker2.tag = 2
        marker2.mapPoint = MapPoint.mapPointWithGeoCoord(
            37.2360253223934200000000000,
            127.1904078627957000000000000
        )
        marker2.markerType = MapPOIItem.MarkerType.BluePin
        marker2.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        val markers = arrayOf(marker, marker2)
        mapView.addPOIItems(markers)
//        mapView.setPOIItemEventListener(eventListener)

        binding.clKakaoMap.addView(mapView)
    }

    class MarkerEventListener(val context: Context) : MapView.POIItemEventListener {
        override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {

        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
            // 말풍선 클릭 시 (Deprecated)
            // 이 함수도 작동하지만 그냥 아래 있는 함수에 작성하자
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            mapView: MapView?,
            poiItem: MapPOIItem?,
            buttonType: MapPOIItem.CalloutBalloonButtonType?
        ) {
            // 말풍선 클릭 시
            val builder = AlertDialog.Builder(context)
            val itemList = arrayOf("들어가기", "취소")
            builder.setTitle("${poiItem?.itemName}")
            builder.setItems(itemList) { dialog, which ->
                when (which) {
//                    0 -> context.startActivity(Intent(context, DetailActivity::class.java))
//                    1 -> dialog.dismiss()   // 대화상자 닫기
                }
            }
            builder.show()
        }

        override fun onDraggablePOIItemMoved(
            mapView: MapView?,
            poiItem: MapPOIItem?,
            mapPoint: MapPoint?
        ) {
            // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
        }
    }
}