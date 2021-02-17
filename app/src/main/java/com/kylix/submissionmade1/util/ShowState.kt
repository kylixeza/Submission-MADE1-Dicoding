package com.kylix.submissionmade1.util

import com.kylix.submissionmade1.databinding.FavoriteFragmentBinding
import com.kylix.submissionmade1.databinding.FollowFragmentBinding
import com.kylix.submissionmade1.databinding.HomeFragmentBinding

interface ShowState {
    fun onSuccessState(homeFragmentBinding: HomeFragmentBinding? = null,
                  followFragmentBinding: FollowFragmentBinding? = null,
                  favoriteFragmentBinding: FavoriteFragmentBinding? = null)

    fun onLoadingState(homeFragmentBinding: HomeFragmentBinding? = null,
                  followFragmentBinding: FollowFragmentBinding? = null,
                  favoriteFragmentBinding: FavoriteFragmentBinding? = null)

    fun onErrorState(homeFragmentBinding: HomeFragmentBinding? = null,
                followFragmentBinding: FollowFragmentBinding? = null,
                favoriteFragmentBinding: FavoriteFragmentBinding? = null,
                message: String?)
}