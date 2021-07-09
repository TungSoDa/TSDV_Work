import { Answer } from "./answer-model";

export interface Question {
  questionId: number;
  questionContent: string;
  questionImage: string;
  questionTopic: string;
  answerList: Answer[];
}
