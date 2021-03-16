declare module "react-load-script" {
  import React from "react";

  export interface IScriptProps {
    attributes?: object;
    onCreate?: () => void;
    onError?: () => void;
    onLoad: () => void;
    url: string;
  }

  export default class Script extends React.Component {
    props: IScriptProps;
    state: any;
    context: any;
    refs: any;
    forceUpdate(callback: any): void;
    render(): any;
    setState(partialState: any, callback: any): void;
  }
}
