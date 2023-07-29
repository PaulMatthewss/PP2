package com.example.pp2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {
    @GET("common.asmx/getScheduleGroup?group_name={group_name}&week_offset=0")
    Call<List<Lesson>> listLessons(@Path("group_name") String group_name);
}
