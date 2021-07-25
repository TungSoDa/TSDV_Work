import { Answer } from "./answer-model";

export interface Question {
  questionID: number,
  content: string,
  image: string,
  topic: string,
  answerList: Answer[]
}

export class QuestionImpl implements Question {
  questionID!: number;
  content!: string;
  image!: string;
  topic!: string;
  answerList!: Answer[]

  constructor() {}
}
