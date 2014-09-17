package net.randomjoe.materialquotecloud;

import com.google.appengine.api.datastore.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class AddQuotesUtil {

	/*
	 * Static list containing some example quote,
	 * stupid way to do it, but works for this example
	 */

	public static ArrayList<QuoteBean> addQuotesUtil() {
		ArrayList<QuoteBean> quoteBeanArrayList = new ArrayList<QuoteBean>();

		quoteBeanArrayList.add(addAlbertEinstein());
		quoteBeanArrayList.add(addAudreyHepburn());
		quoteBeanArrayList.add(addGeneRoddenberry());
		quoteBeanArrayList.add(addCarlSagan());

		return quoteBeanArrayList;
	}

	public static ArrayList<Text> convertToTextArray(ArrayList<String> strings) {
		ArrayList<Text> texts = new ArrayList<Text>();
		for (String string : strings) {
			texts.add(new Text(string));
		}
		return texts;
	}

	public static ArrayList<String> convertTextToStringArray(ArrayList<Text> texts) {
		ArrayList<String> strings = new ArrayList<String>();
		for (Text text : texts) {
			strings.add(text.getValue());
		}
		return strings;
	}

	private static QuoteBean addAlbertEinstein() {
		return new QuoteBean(1L,
				"Albert Einstein",
				"http://shihengcheong.com/blog/wp-content/uploads/2007/02/albert_einstein.jpg",
				new ArrayList<String>(Arrays.asList(
						"Try not to become a man of success, but rather try to become a man of value",
						"Education is what remains after one has forgotten what one has learned in school.",
						"Any intelligent fool can make things bigger, more complex, and more violent. It takes a touch of genius -- and a lot of courage -- to move in the opposite direction.",
						"The important thing is not to stop questioning. Curiosity has its own reason for existing.",
						"The release of atom power has changed everything except our way of thinking...the solution to this problem lies in the heart of mankind. If only I had known, I should have become a watchmaker.",
						"I never think of the future. It comes soon enough.",
						"Do not worry about your difficulties in mathematics, I can assure you that mine are all greater"
				)));
	}

	private static QuoteBean addAudreyHepburn() {
		return new QuoteBean(2L,
				"Audrey Hepburn",
				"http://cp91279.biography.com/Audrey-Hepburn_A-Life-in-Full-Circle_HD_768x432-16x9.jpg",
				new ArrayList<String>(Arrays.asList(
						"Nothing is impossible, the word itself says ‘I’m possible’!",
						"There is more to sex appeal than just measurements. I don’t need a bedroom to prove my womanliness. I can convey just as much sex appeal picking apples off a tree or standing in the rain.",
						"You can tell more about a person by what he says about others than you can by what others say about him.",
						"I don’t take my life seriously, but I do take what I do in my life seriously",
						"People, even more than things, have to be restored, renewed, revived, reclaimed, and redeemed; never throw out anyone."
				)));
	}

	private static QuoteBean addCarlSagan() {
		return new QuoteBean(3L,
				"Carl Sagan",
				"http://ezramagazine.cornell.edu/SUMMER12/images/Photos/S3.Sagan.jpg",
				new ArrayList<String>(Arrays.asList(
						"We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.",
						"The Cosmos is all that is or was or ever will be. Our feeblest contemplations of the Cosmos stir us -- " +
								"there is a tingling in the spine, a catch in the voice, a faint sensation, as if a distant memory, of falling from a height. We know we are approaching the greatest of mysteries.",
						"Every star may be a sun to someone.",
						"Every one of us is, in the cosmic perspective, precious. If a human disagrees with you, let him live. In a hundred billion galaxies, you will not find another.",
						"Consider again that dot. That's here. That's home. That's us. On it everyone you love, everyone you know, everyone you ever heard of, every human being who ever was, lived out their lives. " +
								"The aggregate of our joy and suffering, thousands of confident religions, ideologies, and economic doctrines, every hunter and forager, every hero and coward, every creator and destroyer of civilization, " +
								"every king and peasant, every young couple in love, every mother and father, hopeful child, inventor and explorer, every teacher of morals, every corrupt politician, every \"superstar\", every \"supreme leader\", " +
								"every saint and sinner in the history of our species lived there - on a mote of dust suspended in a sunbeam.\n" +
								"The Earth is a very small stage in a vast cosmic arena. Think of the rivers of blood spilled by all those generals and emperors so that, in glory and triumph, they could become the momentary masters of a fraction of a dot. " +
								"Think of the endless cruelties visited by the inhabitants of one corner of this pixel on the scarcely distinguishable inhabitants of some other corner, how frequent their misunderstandings, how eager they are to kill one another, how fervent their hatreds.\n" +
								"\n" +
								"Our posturings, our imagined self-importance, the delusion that we have some privileged position in the Universe, are challenged by this point of pale light. " +
								"Our planet is a lonely speck in the great enveloping cosmic dark. In our obscurity, in all this vastness, there is no hint that help will come from elsewhere to save us from ourselves.\n" +
								"\n" +
								"The Earth is the only world known so far to harbor life. There is nowhere else, at least in the near future, to which our species could migrate. " +
								"Visit, yes. Settle, not yet. Like it or not, for the moment the Earth is where we make our stand.\n" +
								"\n" +
								"It has been said that astronomy is a humbling and character-building experience. " +
								"There is perhaps no better demonstration of the folly of human conceits than this distant image of our tiny world. " +
								"To me, it underscores our responsibility to deal more kindly with one another, and to preserve and cherish the pale blue dot, the only home we've ever known."
				)));
	}

	private static QuoteBean addGeneRoddenberry() {
		return new QuoteBean(4L,
				"Gene Roddenberry",
				"http://www.futuredude.com/wp-content/uploads/2012/03/roddenberry-nimoy.jpg",
				new ArrayList<String>(Arrays.asList(
						"The strength of a civilization is not measured by its ability to fight wars, but rather by its ability to prevent them.",
						"Star Trek was an attempt to say that humanity will reach maturity and wisdom on the day that it begins not just to tolerate, but take a special delight in differences in ideas and differences in life forms. " +
								"If we cannot learn to actually enjoy those small differences, to take a positive delight in those small differences between our own kind, here on this planet, " +
								"then we do not deserve to go out into space and meet the diversity that is almost certainly out there",
						"A man either lives life as it happens to him, meets it head-on and licks it, or he turns his back on it and starts to wither away.",
						"Earth is the nest, the cradle, and we'll move out of it."

				)));
	}

}