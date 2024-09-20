/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import { NotificationMentionsType } from '@/types/db';
import Link from 'next/link';
import ReactTimeAgo from 'react-time-ago';
import NotificationCardWrapper from './notification-card-wrapper';

interface NotificationCardProps {
  notification: NotificationMentionsType;
}
const NotificationMentionCard = ({ notification }: NotificationCardProps) => (
  <Link href={`post/${notification.postId}`}>
    <a className="block">
      <NotificationCardWrapper
        imageUrl={notification.postAutor.image}
        isRead={notification.isRead}
        notificationId={notification.id}
      >
        <p className="dark:text-primary-dark-600">
          <span className="font-medium text-primary-800 dark:text-primary-dark-800 mr-1">
            {notification.postAutor.name}
          </span>
          has mentioned you in post
        </p>
        <ReactTimeAgo
          date={notification.createdAt}
          className="font-medium text-xs text-gray-400 dark:text-primary-dark-600"
        />
        <p className="text-primary-600 dark:text-primary-dark-600 text-sm italic mt-3 ring-2 ring-blue-400/30 rounded-md p-2">
          {notification.postContent}
        </p>
      </NotificationCardWrapper>
    </a>
  </Link>
);

export default NotificationMentionCard;
