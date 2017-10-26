package cn.lucode.job.dao;

import cn.lucode.job.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2017/10/24.
 */
@Mapper
public interface ScheduleJobDao {
    void save(ScheduleJobEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<ScheduleJobEntity> list);

    int update(ScheduleJobEntity t);

    int update(Map<String, Object> map);

    int delete(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    ScheduleJobEntity queryObject(Object id);

    List<ScheduleJobEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
