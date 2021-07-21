import { Question } from "./question-model";

export interface Exam {
  examID: number,
  name: string,
  topic: string,
  questionList: Question[]
}
