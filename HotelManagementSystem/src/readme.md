# Patika Turizm Acente Sistemi

Bu proje, Patika Turizm Acentesi'nin işlerini dijital ortamda yürütebilmesi için bir otel yönetim sistemi geliştirmeyi amaçlar. Bu sistem, acente çalışanlarının otel rezervasyonlarını, odaları yönetmelerini ve müşteri taleplerini karşılamalarını sağlayacak kullanıcı dostu bir arayüze sahiptir.

## Özellikler

- **Kullanıcı Yönetimi**: Admin, acente çalışanlarını ekleyebilir, düzenleyebilir, silebilir ve listelerini görüntüleyebilir.
- **Otel Yönetimi**: Otellerin eklenmesi, düzenlenmesi ve listelenmesi sağlanır. Ayrıca otellere ait pansiyon tipleri ve tesis özellikleri kaydedilir.
- **Dönem Yönetimi**: Otel dönemleri eklenir ve fiyatlandırmalar bu dönemlere göre yapılır.
- **Oda Yönetimi**: Otellere ait odaların eklenmesi, düzenlenmesi ve listelenmesi sağlanır. Farklı oda tipleri ve özellikleri kaydedilir.
- **Fiyatlandırma**: Oda fiyatları otel dönemlerine, pansiyon tiplerine ve misafir sayısına göre otomatik olarak hesaplanır.
- **Oda Arama ve Rezervasyon İşlemleri**: Acente çalışanları, müşterilerin taleplerine göre odaları arayabilir ve rezervasyon işlemlerini gerçekleştirebilir.

## Teknik Gereksinimler

- Veritabanı: PostgreSQL kullanılıyor.
- GUI (Arayüz): Swing ile kullanıcı dostu bir arayüz tasarlanmıştır.

## Veritabanı Tabloları

- `user`: Admin ve acente çalışanı kullanıcı bilgilerini tutar.
- `otel`: Otel bilgilerini tutar.
- `season`: Otele ait dönem kayıtlarını tutar.
- `pension`: Otele ait pansiyon tiplerini tutar.
- `room`: Otele ait oda kayıtlarını tutar.
- `reservation`: Odaya ait rezervasyon kayıtlarını tutar.

## Kurulum

1. Projeyi bilgisayarınıza klonlayın.
2. Gerekli bağımlılıkları yüklemek için `npm install` komutunu çalıştırın.
3. Veritabanını için klasörde bulunan `HotelManagementSystemDatabase.sql` dosyasını kullanabilirsiniz.
4. Admin olarak giriş yapmak için `admin` kullanıcı adı ve `1234` şifresi ile giriş yapabilirsiniz. 
5. Personel olarak giriş yapmak için `employee` kullanıcı adı ve `1234` şifresi ile giriş yapabilirsiniz.
6. Operasyonun geneli `EmployeeView` sınıfı içerisinde bulunmaktadır.
7. Programı çalıştırmak için `Main` sınıfını çalıştırabilirsiniz.
8. İyi eğlenceler! 👋🏼         

## Katkılar

Kat Contributions are welcome! For major changes, please open an issue first to discuss what you would like to change.





