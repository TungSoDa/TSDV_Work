import { Question } from "./question-model";

export interface Exam {
  examID: number,
  name: string,
  topic: string,
  questionList: Question[]
}

export class ExamImpl implements Exam {
  examID!: number;
  name!: string;
  topic!: string;
  questionList!: Question[]

  constructor() {}
}
