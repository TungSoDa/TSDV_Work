import { Answer } from "./answer-model";

export interface Question {
  questionId: number;
  content: string;
  image: string;
  topic: string;
  answerList: Answer[];
}
