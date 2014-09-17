package net.randomjoe.materialquote;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import net.randomjoe.materialquotecloud.quoteApi.QuoteApi;
import net.randomjoe.materialquotecloud.quoteApi.model.QuoteBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EndPointsQuotesImpl {

	private static final String LOGTAG = EndPointsQuotesImpl.class.getSimpleName();
	final QuoteApi mQuoteApiService;

	public EndPointsQuotesImpl() {
		QuoteApi.Builder builder = getQuotesFromProduction();
		mQuoteApiService = builder.build();
	}

	private QuoteApi.Builder getQuotesFromProduction() {
		return new QuoteApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
	}

	@SuppressWarnings("unused")
	private QuoteApi.Builder getQuotesFromDebug() {
		return new QuoteApi.Builder(AndroidHttp.newCompatibleTransport(),
				new AndroidJsonFactory(), null)
				.setRootUrl("http://10.0.2.2:8080/_ah/api/")
				.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
					@Override
					public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
							throws IOException {
						abstractGoogleClientRequest.setDisableGZipContent(true);
					}
				});
	}


	public synchronized void pullFromRemote(final Handler handler) {

		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {

					List<QuoteBean> remoteTasks = mQuoteApiService.getQuotes().execute().getItems();

					if (remoteTasks != null) {
						ArrayList<QuotesPerson> quotesArrayList = new ArrayList<QuotesPerson>();
						for (QuoteBean quoteBean : remoteTasks) {
							quotesArrayList.add(new QuotesPerson(quoteBean.getId(), quoteBean.getName(), quoteBean.getUrlImageString(), new ArrayList<String>(quoteBean.getQuotesArray())));
						}

						Message message = handler.obtainMessage(0, quotesArrayList);
						message.sendToTarget();
					}
				} catch (IOException e) {
					Log.e(LOGTAG, "Exception : " + e);
				}
			}
		};

		Thread thread = new Thread(r);
		thread.start();
	}
}
