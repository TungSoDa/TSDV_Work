import { AnswerImpl } from "./answer-model";

export interface Question {
  questionID: number,
  content: string,
  image: string,
  topic: string,
  answerList: AnswerImpl[]
}

export class QuestionImpl implements Question {
  questionID!: number;
  content!: string;
  image!: string;
  topic!: string;
  answerList!: AnswerImpl[];

  constructor() {}
}
