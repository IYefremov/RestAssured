package com.cyberiansoft.test.ios10_client.regularclientsteps;

import com.cyberiansoft.test.dataclasses.QuestionScreenData;
import com.cyberiansoft.test.dataclasses.QuestionsData;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.iOSBaseScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens.RegularBaseWizardScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens.RegularQuestionsScreen;
import com.cyberiansoft.test.ios10_client.types.wizardscreens.WizardScreenTypes;

import java.util.List;

public class RegularQuestionsScreenSteps {

    public static void goToQuestionsScreenAndAnswerQuestions(QuestionScreenData questionScreenData) {
        RegularBaseWizardScreen baseWizardScreen = new RegularBaseWizardScreen();
        baseWizardScreen.selectNextScreen(WizardScreenTypes.QUESTIONS, questionScreenData.getScreenName());
        if (questionScreenData.getQuestionsData() != null)
            answerQuestions(questionScreenData.getQuestionsData());
        if (questionScreenData.getQuestionData() != null)
            answerQuestion(questionScreenData.getQuestionData());
    }

    public static void answerQuestions(List<QuestionsData> questionsData) {
        for (QuestionsData questionData : questionsData) {
            answerQuestion(questionData);
        }
    }

    public static void answerQuestion(QuestionsData questionData) {
        RegularQuestionsScreen questionsScreen = new RegularQuestionsScreen();
        if (questionData.getScreenIndex() > 0) {
            for (int i = 0; i < questionData.getScreenIndex(); i++)
                questionsScreen.swipeScreenUp();
        }
        if (questionData.getQuestionSetionName() != null)
            questionsScreen.waitForQuestionSectionLoad(questionData.getQuestionSetionName());
        if (questionData.getQuestionAnswer() != null)
            questionsScreen.answerQuestion(questionData);
        if (questionData.isSignatureQuestion())
            questionsScreen.drawRegularSignature();

    }
}