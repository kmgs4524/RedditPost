# RedditPost
有無限滑動列表的 demo APP，以 Jetpack MVVM Architecture 為畫面主架構，第一次載入讀取12筆貼文，每次向下滑動刷新載入10筆貼文。
</br>
</br>
![](https://github.com/kmgs4524/RedditPost/blob/master/demo/redditpost_demo.gif)
## Android Development and Architecture
* 使用 RxJava 處理非同步流程
* 使用 Retrofit 串接 Web API
* 使用 Glide 處理圖片顯示與 memory cache 和 disk cache
* 使用 Timber 記錄 log
* Architecture Components. Room, LiveData, ViewModel, Paging
* 使用 Android Debug Database 查看手機資料表