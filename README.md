# Patika & FMSS Bilişim Teknoloji Bootcamp Final Project

# Guide Travel App


  # Tech I Used
  
 Clean Architecture + MVVM
 Hilt
 Navigation Compose
 LiveData
 Kotlin Coroutines
 Retrofit
 Room
 

# 📸 Project Video

https://user-images.githubusercontent.com/59700175/195120660-e495d315-1c2d-475d-bb63-71d97c4228bc.mov

 
 # 📸 Screens
 
 ## Home Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491415.png" width="20%">
  </p>
  
  Tüm veriler API aracılığı gelmektedir. TabLayout itemlarına tıklayınca RecyclerView'ın verileri değişmektedir. RecyclerView'ın herhangi bir item'ına tıklayınca Detail Fragment'a gidilmektedir.
  
 ## Detail Screen - Image Detail Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491423.png" width="20%">
  <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491426.png" width="20%">
  </p>
  
 Tıklanılan item'ın tüm verileri bu ekranda gösterilmektedir.Tüm resimler sol üstteki butona tıklayınca Image Detail Fragment'a gönderilir. Tüm resimler Image Detail Fragment'ta ViewPager yardımıyla slide edilerek görüntülenmektedir. Add Bookmark butonuna tıklayınca Backend'e istek atılarak isBookmark değeri true olarak değiştirilir ve Trip Fragment'a yönlendirilir.
 
 ## Search Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491432.png" width="20%">
  </p>
  
  Bu ekrandaki tüm veriler API aracılığı ile gelmektedir. Liste elemanlarına tıklayınca Detail Fragment'a gidilmektedir. Nearby Attractions kategorisindeki itemların sol üstünde bulunan butona tıklayınca Backend'e istek atılarak isBookmark değeri true olarak değiştirilir. Kullanıcı burada Search işlemi de yapabilmektedir. Kullanıcı aratmak istediği kelimeyi yazdıktan sonra ara butonuna bastığında Search Result ekranına yönlendirilmektedir.
  
   ## Trip Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491437.png" width="20%">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491440.png" width="20%">

  </p>
  
  Üstteki TabLayout sayesinde RecyclerView içindeki veriler değiştirilmektedir. Trips kısmındaki veriler Room database'den gelmektedir. Bookmark kısmındaki veriler ise API aracılığı ile gelmektedir. Bookmark kısmında sadece isBookmark değeri true olan veriler gelmektedir. Liste elemanları sola kaydırıldıklarında kaldırılmaktadırlar. Trips kısmındaki veriler item sola kaydırıldığında room databaseden silinmektedir. Bookmark kısmında ise Backend'e istek atılarak isBookmark değeri false olarak değiştirilmektedir.
  
Floating action button'a tıklanıldığında bottom sheet fragment açılmaktadır. İki adet seçenek sunulmaktadır. Create Your Trip seçeneğinde kullanıcı kendi planladığı gezileri oluşturabilmektedir. Select for bookmark seçeneğinde ise Backend tarafındaki veriler sunularak kullanıcı bookmark kısmına istediği yeri ekleyebilmektedir.

 ## Add Bookmark Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491448.png" width="20%">
 </p>
 
 Tüm veriler API aracılığı ile gelmekte ve RecyclerView içinde gösterilmektedir. Kullanıcı ekle butonuna tıklayarak istediği yerleri bookmark kısmına ekleyebilmektedir.
 
 ## Add Trip Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665496007.png" width="20%">
  <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491474.png" width="20%">

 </p>
 
 Bu ekranda kullanıcı yapmayı planladığı geziyi oluşturabilmektedir. Üstteki ImageView'a tıklayınca Image Search ekranına yönlendirilmektedir. Alttaki Date butonuna tıklayınca DatePicker açılmaktadır. Kullanıcı hangi tarih aralıklarında seyahat etmek istediğini DatePicker ile seçebilmektedir. Tüm bilgiler girildikten sonra kaydet butonuna basılırsa veriler room database'e kaydedilmektedir. Eğer bilgilerden biri bile eksik ise kaydetme işlemi yapılmamakta ve kullanıcıya Toast mesajı gösterilmektedir.
 
 
  ## Search Image Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491487.png" width="20%">
 </p>
 
 Kullanıcı gitmeyi planladığı yerle ilgili resim aratması yapıp istediği resmi seçebilmektedir. Aratılan resimler Pixabay tarafından sunulan API aracılığı ile gelmekte ve RecyclerView ile gösterilmektedir. Resim seçildikten sonra seçilen resmin Url'si Add Trip ekranına aktarılmaktadır.
 
  
  ## Guide Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491500.png" width="20%">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491533.png" width="20%">
 </p>
 
 Bu ekrandaki tüm veriler API aracılığı ile gelmekte ve RecyclerView ile gösterilmektedir. Kullanıcı isterse Top Pick Articles kategorisindeki verileri kaydedilmektedir. Kullanıcı bu ekranda arama işlemi yapabilmektedir. İstediği kelimeyi yazdıktan sonra Search Result ekranına yönlendirilmektedir.
 
   ## Search Result Screen
 
<p align="center">
 <img src="https://github.com/fearlessfigter/project-images-and-video/blob/main/images/Screenshot_1665491539.png" width="20%">
 </p>
 
 Bu ekranda aratılan kelimeye uygun veriler gösterilmektedir. API'den çekilen veriler aratılan kelimeye göre filtrelenmektedir.
 
 
