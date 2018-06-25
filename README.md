# ViewBaseAppTemplate

Giới thiệu về Clean Architecture
-----------------
![https://github.com/simplesoft-duongdt3/ViewBaseAppTemplate/tree/mvp](https://github.com/simplesoft-duongdt3/ViewBaseAppTemplate/blob/master/clean_architecture.png)

Kiến trúc tổng quan
-----------------
![https://github.com/simplesoft-duongdt3/ViewBaseAppTemplate/tree/mvp](https://github.com/simplesoft-duongdt3/ViewBaseAppTemplate/blob/master/clean_architecture_layers.png)

MVP (Model View Presenter)
-----------------
![https://github.com/simplesoft-duongdt3/ViewBaseAppTemplate/tree/mvp](https://github.com/simplesoft-duongdt3/ViewBaseAppTemplate/blob/master/mvp.png)

Một số lưu ý khi sử dụng MVP
-----------------
 * `Presenter` - Chỉ thuần Java/Kotlin, không tồn tại những component của Android (vd: Activity, Context, R...), nếu muốn sử dụng các thành phần này thì phải được bao bọc vào trong các Provider (Navigator, ResourceProvider...)
 
 * `View` - Thực sự là Passive View (Dump View), chỉ phụ trách render view, không tồn tại logic, chuyển tất cả action về Presenter để xử lý logic.


### Conductor

Conductor là framework nhỏ, nhẹ, đầy đủ chức năng để xây dụng ứng dụng Android dạng View-base.

[Link tham khảo Conductor](https://github.com/bluelinelabs/conductor/tree/master)


### KOIN
KOIN là một framework cực kỳ nhỏ gọn cho mục đích dependency injection dành riêng cho Kotlin, chỉ sử dụng functional của Kotlin, không sử dụng proxy, code generation, reflection.

[Link tham khảo KOIN](https://insert-koin.io)
