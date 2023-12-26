package sa.com.mysolutions.stcs.scheduler.api;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import java.util.Map;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
@Component(
        immediate = true,
        property = {
           "cron.expression=0/30 0/1 * 1/1 * ? *"   // scheduler run in every 30 seconds *
        },
        service = SchedulerClass.class
    )
public class SchedulerClass extends BaseMessageListener {

 
    @Override
    protected void doReceive(Message arg0) throws Exception {
        System.out.println("hello...");
    }

 @Activate
    @Modified
    protected void activate(Map<String, Object> properties){
        try {
            String cronExpression = GetterUtil.getString(properties.get("cron.expression"), "cronExpression");
            Trigger jobTrigger = TriggerFactoryUtil.createTrigger(getClass().getName(), getClass().getName(), cronExpression);
            SchedulerEntryImpl schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(),jobTrigger);
            SchedulerEngineHelperUtil.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
           
 

        } catch (Exception e) {
           logger.info(e);
        }
    }

 

    private Log logger = LogFactoryUtil.getLog(SchedulerClass.class.getName());

 

}