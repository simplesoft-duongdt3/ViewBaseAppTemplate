package viewbase.app.demo.com.viewbaseapp.data.service.database

import viewbase.app.demo.com.viewbaseapp.data.entity.UserDb

class UserDataServiceImpl : UserDataService, DbFlowDataService<UserDb>() {
    override fun getClassModel(): Class<UserDb> = UserDb::class.java
}