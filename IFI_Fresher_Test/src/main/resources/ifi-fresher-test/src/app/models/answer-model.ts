export interface Answer {
  answerID: number,
  content: string,
  isCorrect:boolean,
  questionID: number
}

export class AnswerImpl implements Answer {
  answerID!: number;
  content!: string;
  isCorrect!: boolean;
  questionID!: number;

  constructor() {}
}
