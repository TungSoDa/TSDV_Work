import { Answer } from "./answer-model";

export interface Question {
  questionID: number;
  content: string;
  image: string;
  topic: string;
  answerList: Answer[];
}
