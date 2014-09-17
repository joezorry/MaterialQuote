/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package net.randomjoe.materialquotecloud;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;

import java.util.ArrayList;
import java.util.List;


@Api(name = "quoteApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "materialquotecloud.randomjoe.net", ownerName = "materialquotecloud.randomjoe.net", packagePath=""))
public class QuoteApi {

	public QuoteApi() {
		//Stupid way to store quotes but works for this example
		storeQuotes();
	}

	@ApiMethod(name = "storeQuote")
	public void storeTask(QuoteBean quoteBean) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastoreService.beginTransaction();
		try {
			Key taskBeanParentKey = KeyFactory.createKey("QuoteBeanParent", "quote_material");
			Entity taskEntity = new Entity("QuoteBean", quoteBean.getId(), taskBeanParentKey);
			taskEntity.setProperty("name", quoteBean.getName());
			taskEntity.setProperty("imageurl", quoteBean.getUrlImageString());
			taskEntity.setProperty("quotearray", AddQuotesUtil.convertToTextArray(quoteBean.getQuotesArray()));
			datastoreService.put(taskEntity);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
	}

	@ApiMethod(name = "getQuotes")
	public ArrayList<QuoteBean> getTasks() {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Key taskBeanParentKey = KeyFactory.createKey("QuoteBeanParent", "quote_material");
		Query query = new Query(taskBeanParentKey);
		List<Entity> results = datastoreService.prepare(query).asList(FetchOptions.Builder.withDefaults());

		ArrayList<QuoteBean> taskBeans = new ArrayList<QuoteBean>();
		for (Entity result : results) {
			QuoteBean quoteBean = new QuoteBean();
			quoteBean.setId(result.getKey().getId());
			quoteBean.setName((String) result.getProperty("name"));
			quoteBean.setUrlImageString((String) result.getProperty("imageurl"));
			quoteBean.setQuotesArray(AddQuotesUtil.convertTextToStringArray((ArrayList<Text>) result.getProperty("quotearray")));
			taskBeans.add(quoteBean);
		}

		return taskBeans;
	}

	private void storeQuotes() {
		for (QuoteBean quoteBean : AddQuotesUtil.addQuotesUtil()) {
			storeTask(quoteBean);
		}
	}
}
