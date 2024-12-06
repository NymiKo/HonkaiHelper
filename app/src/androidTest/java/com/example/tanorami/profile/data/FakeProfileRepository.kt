package com.example.tanorami.profile.data

//class FakeProfileRepository @Inject constructor(): ProfileRepository {
//    private val _profileFlow: MutableStateFlow<NetworkResult<User>?> =
//        MutableStateFlow(null)
//    override val profileFlow: StateFlow<NetworkResult<User>?> =
//        _profileFlow.asStateFlow()
//
//    override suspend fun getProfile() {
//
//    }
//
//    override suspend fun loadAvatar(file: File): NetworkResult<Unit> {
//        return NetworkResult.Success(Unit)
//    }
//
//    override fun clearProfile() {
//
//    }
//}